import React from 'react'
import '../scss/GetTicketStyles.css'
import allocateSlot from '../Services/allocateSlot'


interface GetTicketState{
    vehicleNo: string;
    slotNo: number;
    getTicketCalled:boolean;
    serverResponse: string;
}

class GetTicket extends React.Component<{}, GetTicketState>{
    constructor(props:any) {
        super(props)
    
        this.state = {
            vehicleNo:"",
            slotNo:-1,
            getTicketCalled:false,
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
                this.updateTheSlotNumber(returnedSlot.slotNo);
                console.log(returnedSlot.slotNo);
            })
            .catch((error) =>{
                if (error.response) {
                    this.setState({serverResponse:error.response.data.message});
                }
                this.updateGetTicketCalled();
            });
    }
    
    updateTheSlotNumber = (slotNumber:number) => {
        this.setState((prevState) => ({
            slotNo: slotNumber,
            getTicketCalled: true
        }))
    }

    updateGetTicketCalled = () =>{
        this.setState((prevState)=>({
            getTicketCalled: true
        }))
    }
    
    render() {
        const {vehicleNo, slotNo, getTicketCalled, serverResponse} = this.state;
        let message:string;
        if(slotNo === -1){
            message = serverResponse
        }
        else{
            message = `Welcome ${this.state.vehicleNo}, your parking slot number is ${this.state.slotNo}`
        }
        return (
            getTicketCalled ? 
                <h2>{message}</h2>
                :<div className="popUpDiv">
                    <input type="text" placeholder="License plate number" value={vehicleNo} onChange={this.handleVehicleNoInput}/>
                    <button className="getTicketButton" onClick={this.getTicket}>Get Ticket</button>
                </div>
        )
    }
}

export default GetTicket
