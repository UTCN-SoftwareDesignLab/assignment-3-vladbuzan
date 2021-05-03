import { useCookies } from 'react-cookie'

const useUserCookie = () => {
    
    const [cookies, setCookie, removeCookie] = useCookies(["user"]);

    const saveUserCookie = (user) => {
        removeUserCookie()
        setCookie("user", user, { "path": "/" });
    }

    const removeUserCookie = () => {
        if (cookies.user !== undefined) {
            removeCookie("user", { "path": "/" });
        }
    }

    const getUserCookie = () => {
        return cookies.user
    }

    return [getUserCookie, saveUserCookie, removeUserCookie]
}

export default useUserCookie
