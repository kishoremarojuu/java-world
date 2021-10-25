package sampleServiceImpl;

public class HampHealthPlanImpl implements HealthPlan {

    @Override
    public boolean planProgramLogic(Summary summary) {
        if (summary instanceof HAMPSummary) {
            HAMPSummary hampSummary = (HAMPSummary) summary;
            return checkAndCondition(hampSummary) && checkOrCondition(hampSummary);

        }
        return false;
    }

    private boolean checkAndCondition(HAMPSummary hampSummary) {

        return hampSummary.getP10MimpctCost() >=P10M_IMPACTABLE_COSTS_HAMP &&
                CollectionUtils.isEmpty(hampSummary.getMedEx()) &&
                !hampSummary.isHasCostsRelatedToSingleSurgery() &&
                !hampSummary.isInHospice() &&
                hampSummary.isAMemberOfCarleBh();

    }

    private boolean checkOrCondition(HAMPSummary hampSummary) {

        return hampSummary.isHasDxOfAnxiety() ||
                hampSummary.isHasDxOfDepresion() ||
                hampSummary.isHasDxOfSud() ||
                hampSummary.getRingsSudProbability() >= PROBSUD_HAMP||
                hampSummary.getRingsDepressionProbability() >= PROBDEP_HAMP;
    }
}
