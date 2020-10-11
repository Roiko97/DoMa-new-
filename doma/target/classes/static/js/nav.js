function getCookie() {
    var cookie = document.cookie;
    var arr = cookie.split(";");
    // console.log(arr)
    for(var i =0;i < arr.length;i++){
        var res =arr[i].split("=")[0];
        res = res.replace(/\s+/g,"");
        //if(arr[i].split("=")[0] === " sessionId") {
        if(res ==="sessionId"){
            // console.log("result "+arr[i].split("=")[1]);
            return arr[i].split("=")[1];
        }else{
            // console.log("this is else:"+arr[i].split("=")[0]);
        }
    }
};

function deleteCookie() {
    document.cookie = "sessionId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}


//个人信息显示
let info_div = document.createElement("div");
let name = document.createElement("p");
let num = document.createElement("p");
let grade = document.createElement("p");
let info_btn = document.createElement("button");
let up_div = document.createElement("div");
let span_up = document.createElement("span");
info_div.classList.add("info-part", "hidden");
name.innerHTML = "<strong><img src='src/name.png' alt='用户姓名'></strong>";
num.innerHTML = "<strong><img src='src/grade.png' alt='用户id'></strong>";
grade.innerHTML = "<strong><img src='src/num.png' alt='用户账号'></strong>";
info_btn.classList.add("btn", "btn-danger");
up_div.classList.add("up-div", "text-center");
up_div.setAttribute("onclick", "showInfo()");
span_up.classList.add("glyphicon");
info_btn.setAttribute("onclick", "outLogin()");
info_btn.innerHTML = "注销";
up_div.appendChild(span_up);
info_div.appendChild(name);
info_div.appendChild(grade);
info_div.appendChild(num);
info_div.appendChild(document.createElement("hr"));
info_div.appendChild(info_btn);

info_div.appendChild(up_div);

document.body.appendChild(info_div);

//控制导航栏隐藏和显示
function navStatus() {
    $(".navbar-left").toggleClass("hidden");
}

//显示个人信息
function showInfo() {
    let info = document.getElementsByClassName("info-part")[0];
    // document.querySelector(".nav-top-right").classList.add("hidden");
    let state = info.classList.toggle("hidden");
    if (!state) {
        var user=getCookie();
        var user1=JSON.parse(user);
        info.children[0].innerHTML = "<strong>用户姓名：</strong>" + user1.userName;
        info.children[1].innerHTML ="<strong>用户id：</strong>" + user1.userId;
        info.children[2].innerHTML ="<strong>用户账号：</strong>" + user1.account;
    }
}
//注销
function outLogin() {
    deleteCookie();
    location.href="/ToLogin"
    // $.ajax({
    //     type: "post",
    //     url: "http://localhost:8989/login/userCancellation",
    //     data: {
    //         sessionId: getCookie(),
    //     },
    //     dataType: "JSON",
    //     success: function(re) {
    //         console.log(re);
    //         deleteCookie();
    //         location.href = "/coordination/jump/home";
    //     },
    //     error: function() {
    //         alert("注销失败！ ");
    //     }
    // });
}
//获取当前队伍
(function getTeam() {
    var anchor = document.getElementsByClassName("title-select")[0];
    // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8989/team/teamFind",
    //     data: {
    //         sessionId: getCookie(),
    //     },
    //     dataType: "JSON",
    //     success: function (response) {
    //         var arr = response.team;
    //         var option;
    //         if (arr == null) {
    //             anchor.remove();
    //         } else {
    //             arr.forEach(e => {
    //                 option = document.createElement("option");
    //                 option.innerHTML = e.heading;
    //                 option.value = e.mark;
    //                 anchor.append(option);
    //             });
    //             anchor.value = response.mark;
    //             anchor.classList.remove("hidden");
    //             getTask();
    //         }
    //     }
    // });
})();

//切换队伍功能
function amendTeam(obj) {
    // $.ajax({
    //     type: "POST",
    //     url: "http://localhost:8989/team/teamChange",
    //     data: {
    //         mark: obj.value,
    //         sessionId: getCookie(),
    //     },
    //     dataType: "JSON",
    //     success: function (response) {
    //         var result = response.result;
    //         if (result) {
    //             window.location.reload();
    //         } else {
    //             alert("切换失败");
    //         }
    //     }
    // });
}
