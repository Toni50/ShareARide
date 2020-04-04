import Cookie from "js-cookie";

export function formatDate(dateStr) {
    var d = new Date(dateStr);
    return d.getDate() + "." +
        (d.getMonth()+1) + "." +
        d.getFullYear() + " " +
        d.getHours() + ":" +
        d.getMinutes()
}

export function userAuthenticated() {
    const token = Cookie.get("jwt");
    return token != null;
}



