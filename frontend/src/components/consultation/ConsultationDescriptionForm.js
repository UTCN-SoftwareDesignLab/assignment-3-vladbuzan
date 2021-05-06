import React, { useState } from 'react'
import { Button, Form, Input, Label } from 'semantic-ui-react'

const ConsultationDescriptionForm = ({onSubmitClick}) => {

    const [description, setDescription] = useState('')

    return (
        <Form>
            <Form.Field>
                <Label>
                    New description
                </Label>
                <Input
                    value={description}
                    onChange={event => setDescription(event.target.value)}
                />
            </Form.Field>
            <Button
                onClick={() => onSubmitClick(description)}
            >
                Submit
            </Button>
        </Form>
    )
}

export default ConsultationDescriptionForm
