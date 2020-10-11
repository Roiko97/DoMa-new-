//登录功能
function login(obj) {
    //表单提交
    var name = document.getElementsByName("student_id")[0];
    var na_value = name.value;
    var password = document.getElementsByName("password")[0];
    var pas_value = password.value;
    if (na_value != ""&&pas_value!="" ) {
        $.ajax({
            type:'post',
            url:"login",
            dataType:'json',
            data:{
                account : na_value,
                password: pas_value,
            },
            success: function (response) {
                var result = response.msg;
                var user=response.user;
                if (result=="login success"){
                    var sessionId=JSON.stringify(user)
                    document.cookie="sessionId=" + sessionId;
                    window.location.href="/Communicate"
                }else{
                    alert("账号或密码错误！请重新登录");
                    location.reload()
                }
                // var sessionId = response.sessionId;
                // document.cookie = "sessionId=" + sessionId+";path=/;";
                //document.cookie = "sessionId = " + sessionId;
                // if (result) {
                //      if ("123456" == pas_value)
                //         changePassword();
                //      else{}
                //          // window.location.href = "/coordination/jump/Summarize?id="+sessionId;
                // } else {
                //     alert("账号或密码错误！");
                //     location.reload();
                // }
            },
            error:function (err) {
                console.log(err);
            }
        });
    } else
        return;
}
//回车事件
document.onkeydown = function (event) {
    var e = event || window.event;
    if (e && e.keyCode == 13) { //回车键的键值为13
        $("#btn-submit").click(); //调用登录按钮的登录事件
    }
};
//修改密码样式
function changePassword() {

    $("#login-title").text("修改密码");
    var form = document.getElementById("login");
    //删除登录框
    for (x of form.childNodes) {
        if (x.nodeType == 1) {
            form.removeChild(x);
        }
    }
    //添加修改密码form
    var form_group = document.createElement("div");
    var input_group = document.createElement("div");
    var form_group_addon = document.createElement("span");
    var form_group_input = document.createElement("input");
    var label_password = document.createElement("label");
    form_group.appendChild(input_group);
    input_group.appendChild(form_group_addon);
    input_group.appendChild(form_group_input);

    form_group.classList.add("form-group");
    input_group.classList.add("input-group");
    form_group_addon.classList.add("input-group-addon");
    form_group_addon.innerHTML = "<span class='glyphicon glyphicon glyphicon-lock'></span>";
    form_group_input.classList.add("form-control");
    form_group_input.setAttribute("id","password");
    form_group_input.setAttribute("type","password");
    form_group_input.setAttribute("name","password");
    form_group_input.setAttribute("placeholder","请输入新的密码");
    form_group_input.setAttribute("required","required");
    form_group_input.setAttribute("maxlength","13");
    form_group_input.setAttribute("onkeyup","getPassword()");
    label_password.setAttribute("for","submit");
    label_password.classList.add("control-label","label-password");

    form.appendChild(form_group);
    form.appendChild(label_password);

    //修改按钮
    $("#btn-submit").text("修改")
    $("#btn-submit").attr("onclick","sendNewPassword()");
}
//修改密码
function sendNewPassword(){
    var password = document.getElementById("password").value;
    if (getPassword()){
        // $.ajax({
        //     type: "POST",
        //     url: "http://localhost:8989/login/userRevice",
        //     data: {
        //         "password":password,
        //         sessionId: getCookie(),
        //     },
        //     dataType: "JSON",
        //     success: function (response) {
        //         var result = response.result;
        //         if(result){
        //             window.location.href = "/coordination/jump/toSummarize";
        //         }else{
        //             alert("修改密码失败！");
        //             window.location.href = "/coordination/jump/home";
        //         }
        //     }
        // });
    }else{
        alert("修改失败");
    }
}
//检测密码
function getPassword() {
    var result = checkPassword("label-password", "password");
    var btn = document.getElementById("btn-submit");
    if (result === true) {
        btn.removeAttribute("disabled");
        return true;
    } else {
        btn.setAttribute("disabled", "disabled");
        return false;
    }
}
//密码检测
function checkPassword(label_name, input_id) {
    var label = document.getElementsByClassName(label_name)[0];
    var input = document.getElementById(input_id);
    var str = input.value;
    label.style.color = "red";
    if (str.length < 6) {
        label.innerHTML = "密码长度需要大于6个字符";
        return false;
    }
    if (str.indexOf(" ") != -1) {
        label.innerHTML = "密码不能含有空格!";
        return false;
    }
    // var myreg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
    if (!isNaN(Number(str))) {
        label.innerHTML = "密码必须由字母和数字组成!";
        return false;
    }
    var patrn = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
    if (!patrn.test(str)){
        label.innerHTML = "密码必须由字母和数字组成!";
        return false;
    }
    label.innerHTML = "密码符合规则";
    label.style.color = "green";
    return true;
}