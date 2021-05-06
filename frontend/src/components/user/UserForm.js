import React, { useState } from 'react'
import { Button, Container, Dropdown, Form, Input, Label } from 'semantic-ui-react'

const UserForm = ({ onSubmitClick }) => {

    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [firstname, setFirstname] = useState('')
    const [lastname, setLastname] = useState('')
    const [role, setRole] = useState('REGULAR')

    const roleOptions = [{
        key: 1,
        text: 'Secretary',
        value: 'SECRETARY'
    },
    {
        key: 2,
        text: 'Admin',
        value: 'ADMIN'
    },
    {
        key: 3,
        text: 'Doctor',
        value: 'DOCTOR'
    }]

    const getUserObject = () => {
        return {
            username: username,
            email: email,
            role: role,
            firstname: firstname,
            lastname: lastname
        }
    }

    return (
        <Container>
            <Form>
                <Form.Field>
                    <Label>Username</Label>
                    <Input                        
                        value={username}
                        onChange={(e) => setUsername(e.target.value)} />
                </Form.Field>
                <Form.Field>
                    <Label>Emial</Label>
                    <Input
                        type='email'               
                        value={email}
                        onChange={(e) => setEmail(e.target.value)} />
                </Form.Field>
                <Form.Field>
                    <Label>Firstname</Label>
                    <Input
                        value={firstname}
                        onChange={(e) => setFirstname(e.target.value)} />
                </Form.Field>
                <Form.Field>
                    <Label>Lastname</Label>
                    <Input
                        value={lastname}
                        onChange={(e) => setLastname(e.target.value)} />
                </Form.Field>
                <Form.Field>
                    <Dropdown
                        placeholder='Role'
                        search selection options={roleOptions}
                        onChange={(_, value) => setRole(value.value)} />
                </Form.Field>
                <Button color='black' onClick={() => onSubmitClick(getUserObject())}>
                    Submit
                </Button>
            </Form>
        </Container>
    )
}

export default UserForm