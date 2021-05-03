import React, { useState } from 'react'
import { Container, Form, Input, Label, Button } from 'semantic-ui-react'
import { encodeParams } from '../../api/http'
import { login } from '../../api/services/auth'
import useRedirect from '../../utils/useRedirect'
import useUserCookie from '../../utils/useUserCookie'
const LoginForm = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [getUser, saveUser] = useUserCookie()
    const redirect = useRedirect()

    const onLoginClicked = () => {
        login(encodeParams(
            {
                username,
                password
            }
        )).then((response) => {
            saveUser(response.data)
            redirect(getUser())
        }).catch((reason) => alert(reason))
    }

    return (
        <Container>
            <Form>
                <Form.Field>
                    <Label>
                        Username
                    </Label>
                    <Input
                        placehold='Username'
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
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Field>
                <Button
                    onClick={onLoginClicked}
                    type='LogIn'
                >
                    Log In
                </Button>
            </Form>
        </Container>
    )
}

export default LoginForm