import authHeader, { BASE_URL, encodeParams, HTTP } from '../http'

export const fetchPatients = (user, patientName, page, patientsPerPage) => {
    let request = `/patient?name=${patientName}&page=${page}&patientsPerPage=${patientsPerPage}`
    return HTTP.get(BASE_URL + request, { headers: authHeader(user)})
}

export const savePatient = (user, patient) => {
    return HTTP.post(BASE_URL + '/patient', encodeParams(patient), { headers: authHeader(user)})
}

export const updatePatient = (user, patient, id) => {
    return HTTP.put(BASE_URL + `/patient/${id}`, encodeParams(patient), { headers: authHeader(user)})
}

export const getPatientCount = (user) => {
    return HTTP.get(BASE_URL + '/patient/count', { headers: authHeader(user)})
}
