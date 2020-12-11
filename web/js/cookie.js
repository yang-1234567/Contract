function setCookie(cname, value) {
    let d = new Date();
    d.setTime(d.getTime() + 7 * 24 * 60 * 60 * 1000);
    // d.setTime(d.getTime()+5*1000);
    const expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + value + "; " + expires;
}

function getCookie(cname) {
    const name = cname + "=";
    let ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i].trim();
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
}
