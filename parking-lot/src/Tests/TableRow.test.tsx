import React from "react"
import {configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import TableRow from "../components/TableRow";

configure({adapter: new Adapter()});

describe("ParkingData component testing", () => {
    it("header should have two columns", () => {
        const slot = {
            vehicleNo: "MH29 1233",
            parkingSlot: 1
        }
        const wrapper = shallow(<TableRow slot={slot}/>);
        const element = wrapper.find("td");
        const vehicleNo = element.at(0).text();
        expect(vehicleNo).toBe("MH29 1233");
        const slotNumber = element.at(1).text();
        expect(slotNumber).toBe("1");   

    });
})