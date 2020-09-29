import React from "react"
import {configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import TableRow from "../components/TableRow";

configure({adapter: new Adapter()});

describe("ParkingData component testing", () => {
    it("header should have two columns", () => {
        const Vehicle = {
            vehicleNo: "MH29 1233" 
        }
        const slot = {
            vehicle: Vehicle,
            slotNo: 1
        }
        const onExit = (vehicleNo:string, slotNo:number) => {};
        const wrapper = shallow(<TableRow slot={slot} onExit={onExit}/>);
        const element = wrapper.find("td");
        const vehicleNo = element.at(0).text();
        expect(vehicleNo).toBe("MH29 1233");
        const slotNumber = element.at(1).text();
        expect(slotNumber).toBe("1");   

    });
})