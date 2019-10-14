//生产商信息输入权限验证
function licenseP(Address,callback)
{
    //根据当前MetaMask中的以太坊账号进行验证是否有权限写入
    var postAddress = {"Address":Address};

    $.ajax({
        type: 'POST',
        url: "/licenseP",
        dataType: "json",
        data: JSON.stringify(postAddress),
        contentType: "application/json",
        success: function (R) {
            var resultbad =JSON.stringify(R.Result);
            console.log("license controller");
            console.log(resultbad);
            var result = resultbad.slice(1,(resultbad.length-1));
            if(result=="no signup")
            {
                alert("当前以太坊账号未登记无法进行输入操作");
                callback("ban");
            }
            else if(result == "wrong type")
            {
                alert("请选择当前以太坊账号对应类型输入");
                callback("ban");
            }
            else
            {
                callback("pick");
            }
        },
        error: function () {
            console.log("error");
        }
    });

}

//运输商信息输入权限验证
function licenseT(Address,callback)
{
    var postAddress = {"Address":Address};
    $.ajax({
        type: 'POST',
        url: "/licenseT",
        dataType: "json",
        data: JSON.stringify(postAddress),
        contentType: "application/json",
        success: function (R) {
            var resultbad =JSON.stringify(R.Result);
            console.log("license controller");
            console.log(resultbad);
            var result = resultbad.slice(1,(resultbad.length-1));
            if(result=="no signup")
            {
                alert("当前以太坊账号未登记无法进行输入操作");
                callback("ban");
            }
            else if(result == "wrong type")
            {
                alert("请选择当前以太坊账号对应类型输入");
                callback("ban");
            }
            else
            {
                callback("pick");
            }
        },
        error: function () {
            console.log("error");
        }
    });

}

//经销商信息输入权限验证
function licenseF(Address,callback)
{
    var postAddress = {"Address":Address};
    $.ajax({
        type: 'POST',
        url: "/licenseF",
        dataType: "json",
        data: JSON.stringify(postAddress),
        contentType: "application/json",
        success: function (R) {
            var resultbad =JSON.stringify(R.Result);
            console.log("license controller");
            console.log(resultbad);
            var result = resultbad.slice(1,(resultbad.length-1));
            if(result=="no signup")
            {
                alert("当前以太坊账号未登记无法进行输入操作");
                callback("ban");
            }
            else if(result == "wrong type")
            {
                alert("请选择当前以太坊账号对应类型输入");
                callback("ban");
            }
            else
            {
                callback("pick");
            }
        },
        error: function () {
            console.log("error");
        }
    });

}
