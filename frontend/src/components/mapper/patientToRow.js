import { Button, Table } from 'semantic-ui-react'

export const patientToRow = (patient, button) => {

    return (
        <Table.Row key={patient.id}>
            <Table.Cell>
                <Button
                    positive
                    onClick={() => button.onClick(patient.id)}
                >
                    {button.text}
                </Button>
            </Table.Cell>
            <Table.Cell>
                {patient.firstname}
            </Table.Cell>
            <Table.Cell>
                {patient.lastname}
            </Table.Cell>
            <Table.Cell>
                {patient.birthdate}
            </Table.Cell>
            <Table.Cell>
                {patient.cnp}
            </Table.Cell>
            <Table.Cell>
                {patient.address}
            </Table.Cell>
        </Table.Row>
    )
}