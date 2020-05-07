package com.sample.components;

public class Cabinet extends Component {

    private transient String resolution;
    private transient String size;
    private transient String responseTime;


    public Cabinet(String componentID, String componentType, String componentName, double componentPrice) {
        super(componentID, componentType, componentName, componentPrice);
    }

    @Override
    public void setDescription(){

    }


    public void setResolution(){

    }

    public String getResoltion(){

    }

    public void setSize(){

    }

    public String getSize(){

    }

    public void setResponseTime(){

    }

    public String getResponseTime(){

    }
}
