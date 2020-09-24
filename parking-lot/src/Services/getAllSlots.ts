import axios from 'axios';

function getAllSlots() {
    return axios.get(`/vehicles`);
}

export default getAllSlots
