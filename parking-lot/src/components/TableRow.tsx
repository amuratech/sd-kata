import React from 'react'

interface TableRowProps{
    slot: Slot;
    onExit: (vehicleNo:string, slotNo:number) => void
}
function TableRow(props:TableRowProps) {
    return (
        <tr>
            <td>{props.slot.vehicle.vehicleNo}</td>
            <td>{props.slot.slotNo}</td>
            <td><button onClick={() => props.onExit(props.slot.vehicle.vehicleNo, props.slot.slotNo)}>Exit</button></td>
        </tr>
    )
}

export default TableRow
