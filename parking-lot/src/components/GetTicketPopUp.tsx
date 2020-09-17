import React from 'react'
import '../css/GetTicketPopUpStyles.css'
import axios from "axios"
import {Modal, Button} from 'react-bootstrap';

interface GetTicketPopUpState{
    vehicleNo: string;
    parkingSlot: number;
    isSlotSet:boolean;
}
interface GetTicketPopUpProps{
    onClose: () => void
}
class GetTicketPopUp extends React.Component<GetTicketPopUpProps, GetTicketPopUpState>{
    constructor(props:GetTicketPopUpProps) {
        super(props)
    
        this.state = {
            vehicleNo:"",
            parkingSlot:-1,
            isSlotSet:false 
        }
    }

    handleVehicleNoInput = (e: React.FormEvent<HTMLInputElement>) => {
        this.setState({
            vehicleNo: e.currentTarget.value
        })
    }
   
    getTicket = () => {
        axios.post(`/vehicles/?vehicleNo=${this.state.vehicleNo}`)
            .then(res => {
                const returnedSlot:Slot = res.data;
                this.updateTheSlotNumber(returnedSlot.parkingSlot);
            })
            .catch((error) =>{
                if (error.response) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                }
                this.updateIsSlotSet();
            });
    }
    
    updateTheSlotNumber = (slotNumber:number) => {
        this.setState((prevState) => ({
            vehicleNo: prevState.vehicleNo,
            parkingSlot: slotNumber,
            isSlotSet: true
        }))
    }

    updateIsSlotSet = () =>{
        this.setState((prevState)=>({
            vehicleNo: prevState.vehicleNo,
            parkingSlot: prevState.parkingSlot,
            isSlotSet: true
        }))
    }
    
    render() {
        const {vehicleNo, parkingSlot, isSlotSet} = this.state;
        let message:string;
        if(parkingSlot === -1){
            message = "The parking lot is full"
        }
        else{
            message = `Your parking slot number is ${this.state.parkingSlot}`
        }
        return (
            isSlotSet ? 
            <Modal show={true} centered >
                <Modal.Body as="h2">{message}</Modal.Body>
                <Modal.Footer>
                    <Button onClick = {this.props.onClose}>Close</Button>
                </Modal.Footer>
            </Modal>
            :<div className="popUpDiv">
                <input type="text" placeholder="License plate number" value={vehicleNo} onChange={this.handleVehicleNoInput}/>
                <button className="getTicketButton" onClick={this.getTicket}>Get Ticket</button>
            </div>
        )
    }
}

export default GetTicketPopUp
