import { useHistory } from 'react-router'

const useRedirect = () => {
    
    const history = useHistory()

    const redirect = (user) => {
        switch(user.role) {
            case 'ADMIN':
                history.push('/admin')
                break
            case 'DOCTOR':
                history.push('/medic')
                break
            case 'SECRETARY':
                history.push('/secretary')
                break
            default : 
                history.push('/')
                break
        }
    }

    return redirect
}

export default useRedirect
