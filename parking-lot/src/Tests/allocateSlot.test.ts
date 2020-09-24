import allocateSlot from "../Services/allocateSlot";
import axios from "axios";

jest.mock('axios');
const mockAxios = axios as jest.Mocked<typeof axios>;

describe("allocateSlot testing", () => {
    it("return allocated slot", () => {
        mockAxios.post.mockImplementationOnce(() => Promise.resolve({
            data:[{'vehicleNo': "MH24 1266", 'parkingSlot': 1}]
        }))
        const response = allocateSlot('MH24 1266');
        response.then(res => {expect(res.data).toStrictEqual([{'vehicleNo': "MH24 1266", 'parkingSlot': 1}])})
                .catch(error => console.log(error))
        expect(mockAxios.post).toHaveBeenCalledTimes(1);
    });
});
