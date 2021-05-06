import React, { useState } from 'react'
import { Button, Container, Form, Input, Label } from 'semantic-ui-react'
import SemanticDatepicker from 'react-semantic-ui-datepickers';

const ConsultationForm = ({ onSubmitClick }) => {

    const [doctorId, setDoctorId] = useState(0)
    const [patientId, setPatientId] = useState(0)
    const [description, setDescription] = useState('')
    const [startDate, setStartDate] = useState(new Date())
    const [hour, setHour] = useState('')
    const [duration, setDuration] = useState(0)
    const dateFormat = require('dateformat');

    const getConsultationObject = () => {
        return {
            doctorId: doctorId,
            patientId: patientId,
            description: description,
            date: dateFormat(startDate, 'd-m-yyyy') + ' ' + hour,
            duration: duration
        }
    }

    return (
        <Container>
            <Form>
                <Form.Field>
                    <Label>
                        doctor id
                    </Label>
                    <Input
                        type='number'
                        value={doctorId}
                        onChange={event => setDoctorId(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        patient id
                    </Label>
                    <Input
                        type='number'
                        value={patientId}
                        onChange={event => setPatientId(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Description
                    </Label>
                    <Input
                        value={description}
                        onChange={event => setDescription(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Consultation date
                    </Label>
                    <SemanticDatepicker
                        onChange={(_, data)=> setStartDate(data.value)}
                        time={true}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Hour
                    </Label>
                    <Input
                        value={hour}
                        onChange={event => setHour(event.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Duration
                    </Label>
                    <Input
                        type='number'
                        value={duration}
                        onChange={event => setDuration(event.target.value)}
                    />
                </Form.Field>
                <Button
                    onClick={() => onSubmitClick(getConsultationObject())}
                >
                    Submit
                </Button>
            </Form>
        </Container>
    )
}

export default ConsultationForm
