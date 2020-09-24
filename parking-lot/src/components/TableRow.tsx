import React from 'react'

interface TableRowProps{
    slot: Slot;
    onExit: (vehicleNo:string, slotNo:number) => void
}
function TableRow(props:TableRowProps) {
    return (
        <tr>
            <td>{props.slot.vehicleNo}</td>
            <td>{props.slot.parkingSlot}</td>
            <td><button onClick={() => props.onExit(props.slot.vehicleNo, props.slot.parkingSlot)}>Exit</button></td>
        </tr>
    )
}

export default TableRow
