import React, { useState } from 'react'
import { Container, Form, Input, Label, Dropdown, Button } from 'semantic-ui-react'
import { encodeParams } from '../../api/http'
import { register } from '../../api/services/auth'

const RegisterForm = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [email, setEmail] = useState('')
    const [role, setRole] = useState('DOCTOR')
    const [firstname, setFirstname] = useState('')
    const [lastname, setLastname] = useState('')

    const roles = [
        {
            key: 1,
            text: 'Secretary',
            value: 'SECRETARY'
        },
        {
            key: 2,
            text: 'Doctor',
            value: 'DOCTOR'
        },
        {
            key: 3,
            text: 'Admin',
            value: 'ADMIN'
        }
    ]

    const onRegisterClick = () => {
        register(encodeParams(
            {
                username,
                email,
                password,
                role,
                firstname,
                lastname
            }
        )).then((response) => {
            alert(response.data.message)
        }).catch((_) => {
            alert("Couldn't register user")
        })
    }

    return (
        <Container>
            <Form>
                <Form.Field>
                    <Label>
                        Username
                    </Label>
                    <Input
                        placeholder='username'
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Password
                    </Label>
                    <Input
                        type='password'
                        placeholder='Password'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>Email</Label>
                    <Input
                        type='email'
                        placeholder='Email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Dropdown
                        placeholder='Role'
                        search selection options={roles}
                        onChange={(_, value) => setRole(value.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Firstname
                    </Label>
                    <Input
                        placeholder='firstname'
                        value={firstname}
                        onChange={(e) => setFirstname(e.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Lastname
                    </Label>
                    <Input
                        placeholder='lastname'
                        value={lastname}
                        onChange={(e) => setLastname(e.target.value)}
                    />
                    <Button
                        type='Register'
                        onClick={onRegisterClick}
                    >
                        Register
                </Button>
                </Form.Field>
            </Form>
        </Container>
    )
}

export default RegisterForm