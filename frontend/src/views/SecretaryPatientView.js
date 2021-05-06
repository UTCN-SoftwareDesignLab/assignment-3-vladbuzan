import React, { useState, useEffect } from 'react'
import { Container, Input, Modal, Pagination, Button } from 'semantic-ui-react'
import { fetchPatients, savePatient, updatePatient } from '../api/services/patient'
import PatientForm from '../components/patient/PatientForm'
import PatientTable from '../components/patient/PatientTable'
import useUserCookie from '../utils/useUserCookie'

const SecretaryPatientView = () => {
    const [busy, setBusy] = useState(true)
    const [getUser] = useUserCookie()
    const [name, setName] = useState('')
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(0)
    const itemsPerPage = 5
    const [patients, setPatients] = useState([])
    const [showAddPatientForm, setShowAddPatientForm] = useState(false)
    const [showModifyPatientForm, setShowModifyPatientForm] = useState(false)
    const [currentPatient, setCurrentPatient] = useState(0)
    const [render, setRender] = useState(true)

    useEffect(() => {
        setBusy(true)
        fetchPatients(getUser(), name, activePage - 1, itemsPerPage)
            .then((response) => {
                setPatients(response.data.patients)
                setNumberOfPages(response.data.totalPages)
            })
        setBusy(false)
    }, [name, activePage, render])


    const onAddPatientClick = (patient) => {
        savePatient(getUser(), patient)
            .then((response) => {
                alert(response.data.message)
                setRender(!render)
            })
            .catch(reason => alert(reason))
    }

    const onModifyPatientClick = (patient) => {
        updatePatient(getUser(), patient, currentPatient)
            .then((response) => {
                alert(response.data.message)
                setRender(!render)
            })
            .catch(reason => alert(reason))
    }

    return (
        <Container>
            <Container>
                <Input
                    placeholder='Name'
                    onChange={(event) => setName(event.target.value)}
                />
                {busy ?
                    undefined
                    :
                    <PatientTable
                        button={{
                            onClick: (id) => {
                                setCurrentPatient(id)
                                setShowModifyPatientForm(true)
                            },
                            text: 'Modify'
                        }}
                        patients={patients}
                    />}
                <Button
                    onClick={() => setShowAddPatientForm(!showAddPatientForm)}
                >
                    Add patient
                </Button>
            </Container>
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
            <Modal
                onClose={() => setShowAddPatientForm(false)}
                onOpen={() => setShowAddPatientForm(true)}
                open={showAddPatientForm}
            >
                <Modal.Header>
                    Add Patient
                </Modal.Header>
                <Modal.Content>
                    <PatientForm
                        onSubmitClick={onAddPatientClick}
                    />
                </Modal.Content>
            </Modal>
            <Modal
                onClose={() => setShowModifyPatientForm(false)}
                onOpen={() => setShowModifyPatientForm(true)}
                open={showModifyPatientForm}
            >
                <Modal.Header>
                    Modify Patient
                </Modal.Header>
                <Modal.Content>
                    <PatientForm
                        onSubmitClick={onModifyPatientClick}
                    />
                </Modal.Content>
            </Modal>
        </Container>
    )
}

export default SecretaryPatientView
