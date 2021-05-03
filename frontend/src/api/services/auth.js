import { BASE_URL, HTTP} from '../http'

export const login = (data) => {
    return HTTP.post(BASE_URL + '/auth/sign-in', data)
}

export const register = (data)=> {
    return HTTP.post(BASE_URL + '/auth/sign-up', data)
}