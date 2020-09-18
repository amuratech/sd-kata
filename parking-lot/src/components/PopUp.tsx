import React from 'react';
import {Modal, Button} from 'react-bootstrap';
import GetTicket from "./GetTicket";

interface PopUpProps{
    onClose: () => void;
    showPopUp: boolean;
}
function PopUp(props:PopUpProps) {
    return (
        <Modal show={props.showPopUp} centered >
            <Modal.Body>
                <GetTicket />
            </Modal.Body>
            <Modal.Footer>
                <Button onClick = {props.onClose}>Close</Button>
            </Modal.Footer>
        </Modal>
    )
}

export default PopUp
