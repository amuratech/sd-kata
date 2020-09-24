import axios from 'axios';


function allocateSlot(props:string) {
    return axios.post(`/vehicles/?vehicleNo=${props}`)
}

export default allocateSlot

