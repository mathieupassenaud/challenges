package fr.mathieupassenaud.busroutechallenge.model;

import java.util.ArrayList;
import java.util.Optional;

public class Routes {

	
	private static ArrayList<TreeNode<Integer>> stations = new ArrayList<TreeNode<Integer>>();
	
	public Routes(){}
	
	public void buildRoutes(int[] nodesList){
		// create all roots if needed
		for(int node : nodesList){
			if(!stationExists(node))
				stations.add(new TreeNode<Integer>(node));			
			 TreeNode<Integer> root = getStationRoot(node);
			// for each node, create children
			for(int nodeChild : nodesList){
				
				if(nodeChild != root.data)
					root.addChild(nodeChild);
			}
		}
	}
	public boolean stationExists(int stationNumber){
		return getStationRoot(stationNumber) != null;
	}
	
	public TreeNode<Integer> getStationRoot(int stationNumber){
		Optional<TreeNode<Integer>> root = stations.stream().filter(item -> item.data.equals(stationNumber)).findFirst();
		if(root.isPresent())
			return root.get();
		else
			return null;
	}
	
	public boolean routeExist(int dep, int arr){
		TreeNode<Integer> root = getStationRoot(dep);
		if(root == null)
			return false;
		
		return root.children.stream().filter(item -> item.data.equals(arr)).findAny().isPresent();
	}
}
