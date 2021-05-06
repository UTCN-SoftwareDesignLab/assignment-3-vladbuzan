import authHeader, { BASE_URL, HTTP } from "../http"

export const findAllUsers = (user, page, usersPerPage) => {
    let request = `/user?page=${page}&usersPerPage=${usersPerPage}`
    return HTTP.get(BASE_URL + request, { headers: authHeader(user) })
}

export const updateUser = (user, id, data) => {
    return HTTP.put(BASE_URL + `/user/${id}`, data, { headers: authHeader(user) })
}

export const removeUser = (user, id) => {
    return HTTP.delete(BASE_URL + `/user/${id}`, { headers: authHeader(user) })
}