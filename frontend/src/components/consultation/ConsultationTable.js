import React from 'react'
import { Table } from 'semantic-ui-react'
import { consultationToRow } from '../mapper/consultationToRow'

const ConsultationTable = ({ button, consultations }) => {
    return (
        <Table celled selectable>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell />
                    <Table.HeaderCell>Patient</Table.HeaderCell>
                    <Table.HeaderCell>Doctor</Table.HeaderCell>
                    <Table.HeaderCell>Starting</Table.HeaderCell>
                    <Table.HeaderCell>Ending</Table.HeaderCell>
                    <Table.HeaderCell>Description</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {consultations.map((consultation) => {
                    return (consultationToRow(consultation, button))
                })}
            </Table.Body>
        </Table>
    )
}

export default ConsultationTable
