import React, { useState } from 'react'
import { Container, Menu } from 'semantic-ui-react'
import SecretaryConsultationView from '../views/SecretaryConsultationView'
import SecretaryPatientView from '../views/SecretaryPatientView'


const SecretaryPage = () => {

    const [activeItem, setActiveItem] = useState('Patients')

    return (
        <Container>
            <Menu>
                <Menu.Item
                    name='Patients'
                    active={activeItem === 'Patients'}
                    onClick={() => setActiveItem('Patients')}
                >
                </Menu.Item>
                <Menu.Item
                    name='Consultations'
                    active={activeItem === 'Consultations'}
                    onClick={() => setActiveItem('Consultations')}
                >
                </Menu.Item>
            </Menu>
            {activeItem === 'Patients' ?
                <SecretaryPatientView/>
                :
                <SecretaryConsultationView/>
                }
        </Container>
    )
}

export default SecretaryPage