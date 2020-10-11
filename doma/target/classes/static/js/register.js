function register(obj) {
    var name=document.getElementsByName("registerName")[0];
    var registerName_value=name.value;
    var account=document.getElementsByName("registerAccount")[0];
    var registerAccount_value=account.value;
    var password=document.getElementsByName("registerPassword")[0];
    var registerPassword_value=password.value;
    if(registerName_value!=""){
        $.ajax({
            type:'post',
            url:"Registry",
            dataType:'json',
            data:{
                userName: registerName_value,
                account:registerAccount_value,
                password:registerPassword_value,
            },
            success:function (response) {
                var result = response.msg;
                if(result=="fail"){
                    alert("请填写信息");
                }else if(result=="repeat account"){
                    alert("账号重复，请重新输入");
                }else if(result=="fail"){
                    alert("注册失败");
                }else if(result=="success"){
                    alert("注册成功");
                    window.location.href="/ToLogin"
                }

            },
            error:function (err) {
                alert("注册失败");
            }
        })
    }
}