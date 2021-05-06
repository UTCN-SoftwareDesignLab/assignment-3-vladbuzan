import React, { useState, useEffect } from 'react'
import { fetchConsultations, saveConsultation, updateConsultationDate, updateConsultationDoctor, updateConsultationDescription } from '../api/services/consultation'
import { Container, Input, Pagination, Button, Modal } from 'semantic-ui-react'
import useUserCookie from '../utils/useUserCookie'
import ConsultationTable from '../components/consultation/ConsultationTable'
import ConsultationForm from '../components/consultation/ConsultationForm'
import useMessaging from '../utils/useMessaging'

function SecretaryConsultationView() {

    const [getUser] = useUserCookie()
    const [patientName, setPatientName] = useState('')
    const [doctorName, setDoctorName] = useState('')
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(0)
    const [consultations, setConsultations] = useState([])
    const [showAddConsForm, setShowAddConsForm] = useState(false)
    const [showModifyConsForm, setShowModifyConsForm] = useState(false)
    const [consultationId, setConsultationId] = useState(0)
    const [client, , sendMessage] = useMessaging()
    const itemsPerPage = 5

    useEffect(() => {
        fetchConsultations(doctorName, patientName, getUser(), activePage - 1, itemsPerPage)
            .then((response) => {
                setConsultations(response.data.consultations)
                setNumberOfPages(response.data.totalPages)
            })
            .catch(reason => alert(reason))

    }, [activePage])

    const onAddConsSubmitClick = (consultation) => {
        saveConsultation(getUser(), consultation)
        .then(response => {
            let message = 'New consultation with on ' + consultation.date
            alert(response.data.message)
            sendMessage('/app/send', {
                medicId: consultation.doctorId,
                message: message
            }, client)
        })
        .catch(reason => alert(reason))
    }

    const onModifyConsClick = (consultation) => {
        updateConsultationDate(getUser(), consultationId, 
            {
                newDate: consultation.date,
                duration: consultation.duration
            })
        updateConsultationDoctor(getUser(), consultationId, consultation.doctorId)
        updateConsultationDescription(getUser(), consultationId, consultation.description)
    }

    return (
        <Container>
            <Container>
                <Input
                    placeholder='Doctor'
                    onChange={(event) => setDoctorName(event.target.value)}
                />
                <Input
                    placeholder='Patient'
                    onChange={(event) => setPatientName(event.target.value)}
                />
                <ConsultationTable
                    button={{
                        onClick: id => {
                            setConsultationId(id)
                            setShowModifyConsForm(true)
                        },
                        text: 'Modify'
                    }}
                    consultations={consultations}
                />
                <Button
                    onClick={() => setShowAddConsForm(!showAddConsForm)}
                >
                    Add consultation
                </Button>
            </Container>
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
            <Modal
                onClose={() => setShowAddConsForm(false)}
                onOpen={() => setShowAddConsForm(true)}
                open={showAddConsForm}
            >
                <Modal.Header>
                    Add consultation
                </Modal.Header>
                <Modal.Content>
                    <ConsultationForm
                        onSubmitClick={onAddConsSubmitClick}
                    />
                </Modal.Content>
            </Modal>
            <Modal
                onClose={() => setShowModifyConsForm(false)}
                onOpen={() => setShowModifyConsForm(true)}
                open={showModifyConsForm}
            >
                <Modal.Header>
                    Modify consultation
                </Modal.Header>
                <Modal.Content>
                    <ConsultationForm
                        onSubmitClick={onModifyConsClick}
                    />
                </Modal.Content>
            </Modal>
        </Container>
    )
}

export default SecretaryConsultationView
