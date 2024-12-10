function setCookie(name, value, days, isHttpOnly) {
    const date = new Date();
    date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
    const expires = "expires=" + date.toUTCString();
    if(isHttpOnly) {
        document.cookie = name + "=" + value + ";" + expires + ";path=/";
    } else {
        document.cookie = name + "=" + value + ";" + expires + ";path=/;Secure;HttpOnly";
    }
}


function getCookie(name) {
    const cookieArr = document.cookie.split(';'); // Split cookies into an array
    console.log('All Cookies:', document.cookie); // Log the whole cookies string
    for (let i = 0; i < cookieArr.length; i++) {
        const cookie = cookieArr[i].trim(); // Trim leading/trailing spaces
        if (cookie.startsWith(name + '=')) { // Find the cookie by name
            return cookie.substring(name.length + 1); // Return the value after '='
        }
    }
    return null;
}
