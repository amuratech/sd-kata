import React from 'react'
import TableHeader from "./TableHeader"
import TableRow from "./TableRow"
import  '../scss/ParkingTableStyles.css'

interface TableProps{
    slotList: Array<Slot>;
    onExit: (vehicleNo:string, slotNo:number) => void
}

function ParkingTable(props:TableProps) {
    return (
        <table className="parkingTable">
            <tbody>
                <TableHeader />
                {
                    props.slotList.map(slot => {
                        return (
                            <TableRow key={slot.slotNo} slot={slot} onExit={props.onExit}/>
                        )
                    })
                }
            </tbody>
        </table>
    )
}

export default ParkingTable
