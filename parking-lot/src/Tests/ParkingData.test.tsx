import React from 'react';

import {configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import ParkingData from '../components/ParkingData';

configure({adapter: new Adapter()});

describe("ParkingData component testing", () => {
    
    it("should have table", () => {
        const wrapper = shallow(<ParkingData />);
        expect(wrapper.exists('ParkingTable')).toBe(true);
      });
    
    it("should contain an button with text Add", () => {
        const wrapper = shallow(<ParkingData />); 
        const button = wrapper.find("button");
        const text = button.text();
        expect(text).toBe("Add");         
    });

    it("a PopUp should pop when add button is clicked", () => {
        const wrapper = shallow(<ParkingData />);
        const button = wrapper.find(".addNewCarButton");
        button.simulate('click');
        wrapper.update();
        expect(wrapper.exists('PopUp')).toBe(true);
    });
});
