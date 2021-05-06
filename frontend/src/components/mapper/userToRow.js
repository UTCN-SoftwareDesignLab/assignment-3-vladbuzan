import { Button, Table } from 'semantic-ui-react'

export const userToRow = (user, button) => {

    return (
        <Table.Row key={user.id}>
            <Table.Cell>
                <Button positive onClick={() => button.onClick(user.id)}>
                    {button.text}
                </Button>
            </Table.Cell>
            <Table.Cell>
                {user.username}
            </Table.Cell>
            <Table.Cell>
                {user.email}
            </Table.Cell>
            <Table.Cell>
                {user.firstname}
            </Table.Cell>
            <Table.Cell>
                {user.lastname}
            </Table.Cell>
            <Table.Cell>
                {user.role}
            </Table.Cell>
        </Table.Row>
    )
}