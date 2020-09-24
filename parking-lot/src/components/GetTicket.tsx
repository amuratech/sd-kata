import React from 'react'
import '../css/GetTicketStyles.css'
import allocateSlot from '../Services/allocateSlot'


interface GetTicketState{
    vehicleNo: string;
    parkingSlot: number;
    isSlotSet:boolean;
    serverResponse: string;
}

class GetTicket extends React.Component<{}, GetTicketState>{
    constructor(props:any) {
        super(props)
    
        this.state = {
            vehicleNo:"",
            parkingSlot:-1,
            isSlotSet:false,
            serverResponse:""
        }
    }

    handleVehicleNoInput = (e: React.FormEvent<HTMLInputElement>) => {
        this.setState({
            vehicleNo: e.currentTarget.value
        })
    }
   
    getTicket = () => {
        allocateSlot(this.state.vehicleNo)
            .then(res => {
                const returnedSlot:Slot = res.data;
                this.updateTheSlotNumber(returnedSlot.parkingSlot);
            })
            .catch((error) =>{
                if (error.response) {
                    this.setState({serverResponse:error.response.data.message});
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
        const {vehicleNo, parkingSlot, isSlotSet, serverResponse} = this.state;
        let message:string;
        if(parkingSlot === -1){
            message = serverResponse
        }
        else{
            message = `Welcome ${this.state.vehicleNo}, your parking slot number is ${this.state.parkingSlot}`
        }
        return (
            isSlotSet ? 
                <h2>{message}</h2>
                :<div className="popUpDiv">
                    <input type="text" placeholder="License plate number" value={vehicleNo} onChange={this.handleVehicleNoInput}/>
                    <button className="getTicketButton" onClick={this.getTicket}>Get Ticket</button>
                </div>
        )
    }
}

export default GetTicket
