import React, { useState } from 'react'
import { Button, Container, Form, Input, Label } from 'semantic-ui-react'
import SemanticDatepicker from 'react-semantic-ui-datepickers';
import 'react-semantic-ui-datepickers/dist/react-semantic-ui-datepickers.css';

const PatientForm = ({ onSubmitClick }) => {

    const dateFormat = require('dateformat');
    const [firstname, setFirstname] = useState('')
    const [lastname, setLastname] = useState('')
    const [cnp, setCnp] = useState('')
    const [startDate, setStartDate] = useState(new Date())
    const [address, setAddress] = useState('')

    const getPatientObject = () => {
        return {
            firstname: firstname,
            lastname: lastname,
            cnp : cnp,
            address: address,
            birthdate: dateFormat(startDate, 'd-m-yyyy')
        }
    }

    return (
        <Container>
            <Form>
                <Form.Field>
                    <Label>
                        Firstname
                    </Label>
                    <Input
                        value={firstname}
                        onChange={(event) => setFirstname(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Lastname
                    </Label>
                    <Input
                        value={lastname}
                        onChange={(event) => setLastname(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        CNP
                    </Label>
                    <Input
                        value={cnp}
                        onChange={(event) => setCnp(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Address
                    </Label>
                    <Input
                        value={address}
                        onChange={(event) => setAddress(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Birthdate
                    </Label>
                    <SemanticDatepicker
                        onChange={(_, data)=> setStartDate(data.value)}
                    />
                </Form.Field>
                <Button
                    onClick={() => onSubmitClick(getPatientObject())}
                >
                    Submit
                </Button>
            </Form>
        </Container>
    )
}

export default PatientForm
