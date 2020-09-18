import React from 'react'

interface TableRowProps{
    slot: Slot
}
function TableRow(props:TableRowProps) {
    return (
        <tr>
            <td>{props.slot.vehicleNo}</td>
            <td>{props.slot.parkingSlot}</td>
        </tr>
    )
}

export default TableRow
