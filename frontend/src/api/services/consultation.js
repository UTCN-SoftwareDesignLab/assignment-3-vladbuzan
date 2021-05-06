import authHeader, { BASE_URL, encodeParams, HTTP } from '../http'

export const fetchConsultations = (doctor, patient, user, page, consPerPage) => {
    let request1 = `/consultation?doctor=${doctor}&patient=${patient}`
    let request2 = `&page=${page}&consultationPerPage=${consPerPage}`
    return HTTP.get(BASE_URL + request1 + request2, { headers: authHeader(user) })
}

export const updateConsultationDate = (user, id, data) => {
    return HTTP.patch(BASE_URL + `/consultation/${id}`, encodeParams(data),
        { headers: authHeader(user) })
}

export const updateConsultationDoctor = (user, id, doctorId) => {
    let request = `/consultation/${id}/${doctorId}`
    return HTTP.patch(BASE_URL + request, {}, { headers: authHeader(user) })
}

export const updateConsultationDescription = (user, id, newDescription) => {
    return HTTP.put(BASE_URL + `/consultation/${id}`, {newDescription},
        { headers: authHeader(user) })
}

export const saveConsultation = (user, data) => {
    return HTTP.post(BASE_URL + '/consultation', encodeParams(data), 
    { headers: authHeader(user) })
}

export const fetchConsultationsByMedic = (user, page, consultationsPerPage) => {
    let request = `/consultation/${user.id}?page=${page}&consultationsPerPage=${consultationsPerPage}`
    return HTTP.get(BASE_URL + request, { headers: authHeader(user) })
}