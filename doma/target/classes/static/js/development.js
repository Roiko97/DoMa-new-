//添加成员
function addMember() {
    var id = document.getElementsByName("receiveid")[0].value;
    console.log(id);
    if (id != ""){}
    // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8989/team/sendMessage",
    //     data: {
    //         receiveid: id,
    //         sessionId: getCookie(),
    //     },
    //     dataType: "JSON",
    //     success: function (response) {
    //         var result = response.result;
    //         if(result){
    //             alert("邀请已发送！等待被邀请人同意.");
    //         }else{
    //             alert("邀请失败，存在以下可能，学号不存在、已加入其他队伍、邀请已发送!");
    //             window.location.reload();
    //         }
    //     }
    // });
}
function showMember(arr) {
    var tbody = document.getElementsByTagName("tbody")[0];
    for (var index of arr) {
        var position = index.position == undefined ? '无':index.position;
        var child = createModula(1, "tr", {
            name: ["id"],
            value: [index.id]
        },[
            createModula(1, "td", "", createModula(1, "p", {
                name: ["class"],
                value: ["num"]
            },createModula(3,index.student_id,""))),
            createModula(1, "td", "", createModula(1, "p", {
                name: ["class"],
                value: ["name"]
            }, createModula(3, index.name, ""))),
            createModula(1, "td", "", createModula(1, "select", {
                name:["name","value","onchange"],
                value:["position",position,"amendPosition(this)"]
            },[
                createModula(1,"option",{
                    name:["value"],
                    value:["无"]
                },createModula(3,"无","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["副手"]
                },createModula(3,"副手","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["程序职员"]
                },createModula(3,"程序职员","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["工具维护人员"]
                },createModula(3,"工具维护人员","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["测试人员"]
                },createModula(3,"测试人员","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["项目经理"]
                },createModula(3,"项目经理","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["UI设计师"]
                },createModula(3,"UI设计师","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["开发人员"]
                },createModula(3,"开发人员","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["组长"]
                },createModula(3,"组长","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["编码开发人员"]
                },createModula(3,"编码开发人员","")),
                createModula(1,"option",{
                    name:["value"],
                    value:["测试人员及界面设计人员"]
                },createModula(3,"测试人员及界面设计人员",""))
            ]))
        ]);
        for(var j of child.lastChild.firstChild.childNodes){
            if(j.value == position)
            {
                j.setAttribute("selected","selected");
            }
        }
        tbody.appendChild(child);
    }
}
function getMember() {
    // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8989/team/memberFind",
    //     dataType: "JSON",
    //     data: {
    //         sessionId: getCookie(),
    //     },
    //     success: function (response) {
    //         var member = response.member;
    //         $('#day-number').text("项目截止日期："+(response.endT==undefined?0:response.endT));
    //         if(member != undefined)
    //             showMember(member);
    //     }
    // });
}
//创建组
function createGroup(){
    var endT = $("#endT").val();
    var heading = $("#heading").val();
    if(endT == "" || endT == null){
        return;
    }
    if(heading == "" || heading == null){
        return;
    }
    // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8989/team/teamCreate",
    //     data: {
    //         endT:endT,
    //         heading:heading,
    //         sessionId: getCookie(),
    //     },
    //     dataType: "JSON",
    //     success: function (response) {
    //         var result = response.result;
    //         if(!result){
    //             showAlert(".group-info","创建失败!","danger",function(){
    //             });
    //         }else{
    //             showAlert(".group-info","创建成功，三秒后刷新!","success",function(){
    //                 setTimeout(function(){
    //                 $('.alert').alert('close');
    //                 window.location.reload();
    //             },3000);
    //             });
    //         }
    //     }
    // });
}
document.onloadend = getMember();
function pause(){
    $("#infoCarousel").carousel('pause');
}
//开始轮播
function start(){
    $("#infoCarousel").carousel('cycle');
}
function showAlert(rivet,text,state,fn){
    $('.alert').alert('close');
    var alert = createModula(1, "div", {
        name: ["class"],
        value: ["alert alert-"+state]
    }, [
        createModula(1, "a", {
            name: ["class", "data-dismiss"],
            value: ["close", "alert"]
        }, createModula(3, "关闭")),
        createModula(1, "strong", "", createModula(3, "提示!")),
        createModula(3, text)
    ])
    $(alert).insertAfter($(rivet));
    fn();
}

//离开组
function leaveGroup(){
    var re = confirm("确认离开当前队伍?");
    if(re){}
    // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8989/team/leaveTeam",
    //     dataType: "JSON",
    //     data: {
    //         sessionId: getCookie(),
    //     },
    //     success: function (response) {
    //         var result = response.result;
    //         if(result){
    //             window.location.reload();
    //         }else{
    //             alert("离开失败");
    //         }
    //     }
    // });
}