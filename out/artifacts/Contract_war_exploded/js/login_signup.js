let isPhone1Valid = true;
let isPhone2Valid = true;
let isPassword1Valid = true;
let isPassword2Valid = true;
let isPasswordConValid = true;
// if(getCookie("curUsername") !== ""){
//     window.close();
//     window.open("../index.html");
// }
function cambiar_login() {

    const username = getCookie("lastUsername");
    const password = getCookie("lastPassword");
    if (username !== undefined) {
        document.getElementById("phoneNumber1").value = username;
        document.getElementById("password1").value = password;
    }

    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
    document.querySelector('.cont_form_login').style.display = "block";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_login').style.opacity = "1";
    }, 400);

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
    }, 200);
}

function cambiar_sign_up() {
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
    document.querySelector('.cont_form_sign_up').style.display = "block";
    document.querySelector('.cont_form_login').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
    }, 100);

    setTimeout(function () {
        document.querySelector('.cont_form_login').style.display = "none";
    }, 400);


}

function ocultar_login_sign_up() {

    document.querySelector('.cont_forms').className = "cont_forms";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";
    document.querySelector('.cont_form_login').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
        document.querySelector('.cont_form_login').style.display = "none";
    }, 500);

}

/*表单验证*/
function validateUserForm(index) {
    const username = document.getElementById("phoneNumber" + index).value;
    if (username === undefined || username === "") return;
    try {
        if (username.length < 4) throw "用户名长度要超过4";//判断长度是否为11

        if (index === 1) {
            isPhone1Valid = true;
            let pswd = getCookie(username + "password");
            if (pswd !== undefined) {                      //如果以前该用户登陆过，自动填充密码
                document.getElementById("password1").value = pswd;
            }
        } else {
            //格式限制：只能包含字母、数字、下划线
            pattern = /^\w+$/;
            if (!pattern.test(username)) throw "用户名只能包含字母，数字和下划线";
            isPhone2Valid = true;
        }

        document.getElementById("hintPhone" + index).innerText = "";
    } catch (err) {
        // alert(err);
        document.getElementById("hintPhone" + index).innerText = "提示:" + err;
        if (index === 1) isPhone1Valid = false;
        else isPhone2Valid = false;
    }
}

function validatePasswordForm(index) {
    const password = document.getElementById("password" + index).value;
    if (password === undefined || password === "") return;
    try {
        let pattern;
        //长度限制：6-16
        if (password.length < 6 || password.length > 16) throw "长度限制:6-16";

        //格式限制：只能包含字母、数字、下划线
        pattern = /^\w+$/;
        if (!pattern.test(password)) throw "密码只能包含字母，数字和下划线";

        // //安全要求：必须同时包含字母和数字
        // const num = /[0-9]/
        // if (!num.test(password)) throw "password should contain numbers."
        // const upper = /[a-z]/, lower = /[A-Z]/;
        // if (!upper.test(password) && !lower.test(password)) throw "password should contain letters."

        if (index === 1) isPassword1Valid = true;
        else isPassword2Valid = true;
        document.getElementById("hintPassword" + index).innerText = "";
    } catch (err) {
        // alert(err);
        if (index === 1) isPassword1Valid = false;
        else isPassword2Valid = false;
        document.getElementById("hintPassword" + index).innerText = "提示:" + err;
    }
}

function validateConfirmForm() {
    const password = document.getElementById("password2").value;
    const passwordCon = document.getElementById("passwordCon").value;
    if (passwordCon === undefined || passwordCon === "") return;
    try {
        if (password !== passwordCon) throw "两次输入不一致"
        isPasswordConValid = true;
        document.getElementById("hintPasswordCon").innerText = "";
    } catch (err) {
        isPasswordConValid = false;
        document.getElementById("hintPasswordCon").innerText = "提示:" + err;
    }
}

function login() {
    const username = document.getElementById("phoneNumber1").value;
    const password = document.getElementById("password1").value;
    try {
        if (!isAllValidLogin()) throw "请填充所有信息";

        verifyLogin(username, password);
    } catch (err) {
        alert(err);
    }

}

function signUp() {
    const username = document.getElementById("phoneNumber2").value;
    const password = document.getElementById("password2").value;

    try {
        if (!isAllValidSignUp()) throw "请填充所有信息";

        verifySignUp(username, password);

    } catch (err) {
        alert(err);
    }
}

function isAllValidLogin() {
    return isPhone1Valid && isPassword1Valid &&
        document.getElementById("phoneNumber1").value !== "" &&
        document.getElementById("password1").value !== "";
}

function isAllValidSignUp() {
    return isPhone2Valid && isPassword2Valid && isPasswordConValid &&
        document.getElementById("phoneNumber2").value !== "" &&
        document.getElementById("password2").value !== "" &&
        document.getElementById("passwordCon").value !== "";
}

function verifyLogin(username, password) {
    var js_send = {
        'username':username,
        'password':password
    }
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            // let js_o = JSON.parse(this.responseText);
            let js_receive = JSON.parse(this.responseText);
            if (js_receive.result === '1') {
                //获取id，权限，存在cookie中
                setCookie("lastUsername", username);
                setCookie("lastPassword", password);
                setCookie(username + "password", password);
                if(js_receive.role !== ""){
                    setCookie("rights",js_receive.rights);
                } else{
                    setCookie("rights","");
                }
                setCookie("curUsername",username);

                // setCookie("rights",js_o.rights);
                window.close();
                window.open("./index.html");
                return true;
            } else {
                alert("用户名或密码错误")
                return false;
            }
        }
    };
    xhttp.open("POST", "http://localhost:8888/Contract/loginServlet", true);
    xhttp.send(JSON.stringify(js_send));
    return false;
}

function verifySignUp(username, password) {
    var js_send = {
        'username':username,
        'password':password
    }
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let js_receive = JSON.parse(this.responseText);
            if (js_receive.result === 1) {
                cambiar_login();
                document.getElementById("phoneNumber1").value = username;
                document.getElementById("password1").value = password;
                return true;
            } else {
                alert("用户名已存在")
                return false;
            }
        }
    };
    xhttp.open("POST", "http://localhost:8888/Contract/registerServlet", );
    xhttp.send(JSON.stringify(js_send));
}
