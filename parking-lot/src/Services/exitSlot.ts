import axios from 'axios';


function exitSlot(props:string) {
    return axios.delete(`/vehicles/?vehicleNo=${props}`)
}

export default exitSlot

