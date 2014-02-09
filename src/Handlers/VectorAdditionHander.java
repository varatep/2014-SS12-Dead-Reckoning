package Handlers;

public class VectorAdditionHander{
	
	public VectorAdditionHander(){
		
	}
	
	public void addVectors(int velocity1, int degree1, int velocity2, int degree2){
			double HnewComp = getHComponent(velocity1, degree1) + getHComponent(velocity2, degree2);
			double VnewComp = getVComponent(velocity1, degree1) + getVComponent(velocity2, degree2);
			
	}
	
	public double getHComponent(int velocity, int degree){
		double HComponent = velocity * Math.cos(degree);
		return HComponent;
	}
	
	public double getVComponent(int velocity, int degree){
		double VComponent = velocity * Math.sin(degree);
		return VComponent;
	}
	
	public double getResultantMagnitude(int HComponent, int VComponent){
		double ResultantMagnitude = Math.sqrt(Math.pow(HComponent, 2) + Math.pow(VComponent, 2));
		return ResultantMagnitude;
	}
}