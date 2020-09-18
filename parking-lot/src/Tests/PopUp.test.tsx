import React from 'react';

import {configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import PopUp from '../components/PopUp';

configure({adapter: new Adapter()});

describe("PopUp component testing", () => {
    const onClose = jest.fn();
    let showPopUp:boolean;
    it("should have close Button", () => {
        const wrapper = shallow(<PopUp onClose={onClose} showPopUp={true}/>);
        expect(wrapper.find('Button').text()).toBe("Close");
      });
    
    it("should have GetTicket Component", () => {
        const wrapper = shallow(<PopUp onClose={onClose} showPopUp={true}/>);
        expect(wrapper.exists('GetTicket')).toBe(true);
    });
    
})