import React, { useState } from 'react'
import { Container, Grid, Segment, Button } from 'semantic-ui-react'
import LoginForm from '../components/auth/LoginForm'
import RegisterForm from '../components/auth/RegisterForm'

const LandingPage = () => {

    const [showLogin, setShowLogin] = useState(true)

    const toggle = () => {
        setShowLogin(!showLogin)
    }

    return (
        <Container>
            <Segment>
                <Grid
                    columns={1}
                    relaxed='very'
                    stackable
                >
                    <Grid.Column>
                        {showLogin ? <LoginForm /> : <RegisterForm />}
                    </Grid.Column>
                    <Grid.Column>
                        {showLogin ?
                            <Button
                                content='Sign up'
                                icon='signup'
                                size='big' onClick={toggle}
                            />
                            : <Button
                                content='Sign in'
                                icon='sign-in'
                                size='big' 
                                onClick={toggle}
                            />}
                    </Grid.Column>
                </Grid>
            </Segment>
        </Container>
    )
}

export default LandingPage