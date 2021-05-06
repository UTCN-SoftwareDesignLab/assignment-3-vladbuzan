import React, { useState, useEffect } from 'react'
import { Container, Modal, Pagination, Message } from 'semantic-ui-react'
import { fetchConsultationsByMedic } from '../api/services/consultation'
import ConsultationDescriptionForm from '../components/consultation/ConsultationDescriptionForm'
import ConsultationTable from '../components/consultation/ConsultationTable'
import { updateConsultationDescription } from '../api/services/consultation'
import useUserCookie from '../utils/useUserCookie'
import useMessaging from '../utils/useMessaging'


const MedicPage = () => {
    const [busy, setBusy] = useState(true)
    const [consultations, setConsultations] = useState([])
    const [getUser] = useUserCookie()
    const [consultationId, setconsultationId] = useState(0)
    const [render, setRender] = useState(true)
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(1)
    const [showDescriptionForm, setShowDescriptionForm] = useState(false)
    const consultationsPerPage = 5
    const [client, subscribe] = useMessaging()
    const [showMessage, setShowMessage] = useState(false)
    const [messageText, setMessageText] = useState('')

    const onMessageReceived = (message) => {
        let received = JSON.parse(message.body)
        if(received !== undefined) {
            if(received.medicId === getUser().id) {
                setMessageText(received.message)
                setShowMessage(true)
            }
            setTimeout(() => setShowMessage(false), 3000);
        }
    }

    useEffect(() => {
        subscribe("/receive", onMessageReceived, client)
    }, [])

    useEffect(() => {
        setBusy(true)
        fetchConsultationsByMedic(getUser(), activePage - 1, consultationsPerPage)
            .then(response => {
                setNumberOfPages(response.data.totalPages)
                setConsultations(response.data.consultations)
            })
            .catch(reason => alert(reason))
        setBusy(false)
    }, [busy, render, activePage])

    const onSubmitDescriptionClick = (description) => {
        updateConsultationDescription(getUser(), consultationId, description)
            .then(response => {
                alert(response.data.message)
                setShowDescriptionForm(false)
                setRender(true)
            })
    }

    return (
        <Container>
            {showMessage? 
                <Message>
                <Message.Header>New consultation!</Message.Header>
                <p>
                  {messageText}
                </p>
              </Message>
            :
            undefined
            }
            <ConsultationTable
                button={{
                    onClick: id => {
                        setconsultationId(id)
                        setShowDescriptionForm(true)
                    },
                    text: 'Set description'
                }}
                consultations={consultations}
            />
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
            <Modal
                onClose={() => setShowDescriptionForm(false)}
                onOpen={() => setShowDescriptionForm(true)}
                open={showDescriptionForm}
            >
                <Modal.Header>
                    Modify description
                </Modal.Header>
                <Modal.Content>
                    <ConsultationDescriptionForm
                        onSubmitClick={onSubmitDescriptionClick}
                    />
                </Modal.Content>
            </Modal>
        </Container>
    )
}
export default MedicPage
