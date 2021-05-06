import React, { useEffect } from 'react'
import { Table } from 'semantic-ui-react'
import { patientToRow } from '../mapper/patientToRow'

const PatientTable = ({button, patients}) => {

    useEffect(() => {}, [patients])

    return (
        <Table celled selectable>
            <Table.Header>
                <Table.Row>
                <Table.HeaderCell />
                    <Table.HeaderCell>Firstname</Table.HeaderCell>
                    <Table.HeaderCell>Lastname</Table.HeaderCell>
                    <Table.HeaderCell>Birthdate</Table.HeaderCell>
                    <Table.HeaderCell>CNP</Table.HeaderCell>
                    <Table.HeaderCell>Address</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                { patients.map((patient) => {
                    return(patientToRow(patient, button))
                })}
            </Table.Body>
        </Table>
    )
}

export default PatientTable
