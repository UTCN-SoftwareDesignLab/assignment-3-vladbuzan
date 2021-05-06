import React, { useState, useEffect } from 'react'
import { Button, Container, Modal, Pagination } from 'semantic-ui-react'
import { encodeParams } from '../api/http'
import { fetchConsultationsByMedic } from '../api/services/consultation'
import { findAllUsers, removeUser, updateUser } from '../api/services/user'
import ConsultationTable from '../components/consultation/ConsultationTable'
import UserForm from '../components/user/UserForm'
import UserTable from '../components/user/UserTable'
import useUserCookie from '../utils/useUserCookie'

const AdminPage = () => {

    const [busy, setBusy] = useState(true)
    const [users, setUsers] = useState([])
    const [getUser] = useUserCookie()
    const [userId, setUserId] = useState(0)
    const [showUpdateForm, setShowUpdateForm] = useState(false)
    const [render, setRender] = useState(true)
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(1)
    const consultationsPerPage = 5

    useEffect(() => {
        setBusy(true)
        findAllUsers(getUser(), activePage - 1, consultationsPerPage)
        .then(response => {
            setNumberOfPages(response.data.totalPages)
            setUsers(response.data.users)
        })
        .catch(reason => alert(reason))
        setBusy(false)
    }, [busy, render, activePage])

    const onUpdateUserClick = (user) => {
        updateUser(getUser(), userId, encodeParams(user))
            .then(response => {
                alert(response.data)
                setRender(!render)
            })

    }

    const onDeleteClick = () => {
        removeUser(getUser(), userId)
            .then(response => alert(response.data.message))
    }

    return(
        <Container>
            <UserTable
                users={users}
                button={{
                    text:'Update',
                    onClick: (id) => {
                        setUserId(id)
                        setShowUpdateForm(true)
                    }
                }}
            />
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
            <Modal
                onClose={() => setShowUpdateForm(false)}
                onOpen={() => setShowUpdateForm(true)}
                open={showUpdateForm}
            >
                <Modal.Header>
                    Update user
                </Modal.Header>
                <Modal.Content>
                    <UserForm
                        onSubmitClick={onUpdateUserClick}
                    />    
                <Button
                    onClick={onDeleteClick}
                >
                    Delete
                </Button>
                </Modal.Content>       
            </Modal>
        </Container>
    )
}

export default AdminPage