import { Button, Table } from 'semantic-ui-react'


export const consultationToRow = (consultation, button) => {
    return (
        <Table.Row key={consultation.details.id}>
        <Table.Cell>
            <Button
                positive
                onClick={() => button.onClick(consultation.details.id)}
            >
                {button.text}
            </Button>
        </Table.Cell>
        <Table.Cell>
            {consultation.patient.firstname + ' ' + consultation.patient.lastname}
        </Table.Cell>
        <Table.Cell>
            {consultation.doctor.firstname + ' ' + consultation.doctor.lastname}
        </Table.Cell>
        <Table.Cell>
            {consultation.details.startDate}
        </Table.Cell>
        <Table.Cell>
            {consultation.details.endDate}
        </Table.Cell>
        <Table.Cell>
            {consultation.details.description}
        </Table.Cell>
    </Table.Row>
    )
}

