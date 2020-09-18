import React from 'react';

import {configure, shallow } from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import TableHeader from '../components/TableHeader';

configure({adapter: new Adapter()});

describe("ParkingData component testing", () => {
    it("header should have two columns", () => {
        const wrapper = shallow(<TableHeader />);
        expect(wrapper.find("tr th")).toHaveLength(2);

    });
})