function setCookie(name, value, days) {
    const date = new Date();
    date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
    const expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

function clearCookie(name, value, days) {
      document.cookie = name +'=;path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function getCookie(name) {
    const cookieArr = document.cookie.split(';');
    console.log('All Cookies:', document.cookie);
    for (let i = 0; i < cookieArr.length; i++) {
        const cookie = cookieArr[i].trim();
        if (cookie.startsWith(name + '=')) {
            return cookie.substring(name.length + 1);
        }
    }
    return "";
}
