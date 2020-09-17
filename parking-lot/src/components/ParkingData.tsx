import React from 'react'
import axios from "axios"
import GetTicketPopUp from "./GetTicketPopUp"
import '../css/ParkingDataStyles.css'

interface ParkingDataState{
    slotList: Array<Slot>
    showPopUp: boolean
}

class ParkingData extends React.Component<{}, ParkingDataState>{
    constructor(props:any) {
        super(props)
    
        this.state = {
            slotList: Array<Slot>(),
            showPopUp: false 
        }
    }

    toggleGetTicketPopUp = () => {
        this.setState((prevState)=>({
            slotList: prevState.slotList,
            showPopUp: !prevState.showPopUp
        }))
    }

    componentDidMount(){
        this.updateSlots();
    }

    updateSlots = () => {
        axios.get(`/vehicles`)
            .then(res => {
                this.updateTheListWithNewVehicle(res.data);
            }).catch(function(error){
                if (error.response) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                    alert("Something went wrong.");
                }
            });
    }

    updateTheListWithNewVehicle = (updateSlotList:Array<Slot>) =>{
        this.setState({
            slotList: updateSlotList
        })
    }

    onCloseModal = () => {
        this.setState((prevState) => ({
            slotList: prevState.slotList,
            showPopUp: false
        }), () => {this.updateSlots()})
    }

    render() {
        const {slotList, showPopUp} = this.state;
        return showPopUp ? 
            <React.Fragment>
                <GetTicketPopUp onClose = {this.onCloseModal}/>  
            </React.Fragment>:
            <div>
                <table className="parkingTable">
                    <tbody>
                        <tr>
                            <th>Vehicle Number</th>
                            <th>Slot Number</th>
                        </tr>
                    
                        {slotList.map(slot => {
                            return (
                                <tr key={slot.parkingSlot}>
                                    <td>{slot.vehicleNo}</td>
                                    <td>{slot.parkingSlot}</td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
                <button className="addNewCarButton"onClick={this.toggleGetTicketPopUp}>Add</button>  
            </div>
    }
}

export default ParkingData
