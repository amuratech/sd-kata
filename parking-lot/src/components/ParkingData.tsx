import React from 'react'
import '../css/ParkingDataStyles.css'

import ParkingTable from "./ParkingTable"
import PopUp from './PopUp'
import getAllSlots from '../Services/getAllSlots'
import exitSlot from '../Services/exitSlot'

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
        getAllSlots()
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

    onExit = (vehicleNo:string, slotNo:number) => {
        exitSlot(vehicleNo).then( ()=> 
            this.setState((prevState) => ({
                slotList: prevState.slotList.filter(slot => slot.parkingSlot != slotNo)
            }))
        ).catch(function(error){
            if (error.response) {
                console.log(error.response.data);
                console.log(error.response.status);
                console.log(error.response.headers);
                alert("Failed to exit ." + error.response.data.message);
            }
        });
    }
    
    render() {
        const {slotList, showPopUp} = this.state;
        return (
            <React.Fragment>
                <PopUp onClose={this.onCloseModal} showPopUp = {showPopUp}/>
                <div>
                    <ParkingTable slotList={slotList} onExit={this.onExit}/>
                    <button className="addNewCarButton"onClick={this.toggleGetTicketPopUp}>Add</button>  
                </div>
            </React.Fragment>
        )
    }
}

export default ParkingData
