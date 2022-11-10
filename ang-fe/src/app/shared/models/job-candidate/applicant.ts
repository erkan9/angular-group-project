import { CandidateStatus } from "../enum/candidate-status";

export interface Applicant {
    userId: string,
    status: string,
    name: string,
    email: string
}